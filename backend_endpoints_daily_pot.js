// Daily Pot API Endpoints for Render Backend
// Add these to your existing Express app

const { createClient } = require('@supabase/supabase-js');

// Initialize Supabase client (should already exist in your app)
const supabase = createClient(
  process.env.SUPABASE_URL,
  process.env.SUPABASE_SERVICE_KEY
);

// Middleware: authenticate user (should already exist)
async function authenticateUser(req, res, next) {
  try {
    const authHeader = req.headers.authorization;
    if (!authHeader || !authHeader.startsWith('Bearer ')) {
      return res.status(401).json({ error: 'No token provided' });
    }

    const token = authHeader.substring(7);
    const { data: { user }, error } = await supabase.auth.getUser(token);

    if (error || !user) {
      return res.status(401).json({ error: 'Invalid token' });
    }

    req.user = user;
    next();
  } catch (error) {
    console.error('Auth error:', error);
    res.status(500).json({ error: 'Authentication failed' });
  }
}

// ==========================================
// GET /daily-pot - Get current pot state
// ==========================================
app.get('/daily-pot', authenticateUser, async (req, res) => {
  try {
    const userId = req.user.id;

    const { data, error } = await supabase.rpc('get_daily_pot_state', {
      p_user_id: userId,
    });

    if (error) {
      console.error('Error fetching pot state:', error);
      return res.status(500).json({ error: error.message });
    }

    if (!data || data.length === 0) {
      // Return default state for new user
      return res.json({
        user_id: userId,
        total_credits: 0,
        uploads_today: 0,
        uploads_required: 5,
        is_unlocked: false,
        has_claimed_today: false,
        last_claim_date: null,
      });
    }

    res.json(data[0]);
  } catch (error) {
    console.error('Daily pot state error:', error);
    res.status(500).json({ error: 'Failed to fetch pot state' });
  }
});

// ==========================================
// POST /daily-pot/upload - Record upload
// ==========================================
app.post('/daily-pot/upload', authenticateUser, async (req, res) => {
  try {
    const userId = req.user.id;

    const { data, error } = await supabase.rpc('record_pot_upload', {
      p_user_id: userId,
    });

    if (error) {
      console.error('Error recording upload:', error);
      return res.status(500).json({ error: error.message });
    }

    const result = data[0];

    // Log unlock event
    if (result.is_unlocked && !result.has_claimed_today) {
      console.log(`🍯 Pot unlocked for user ${userId}: ${result.uploads_today}/${result.uploads_required}`);
    }

    res.json(result);
  } catch (error) {
    console.error('Daily pot upload error:', error);
    res.status(500).json({ error: 'Failed to record upload' });
  }
});

// ==========================================
// POST /daily-pot/claim - Claim pot
// ==========================================
app.post('/daily-pot/claim', authenticateUser, async (req, res) => {
  try {
    const userId = req.user.id;

    const { data, error } = await supabase.rpc('claim_daily_pot', {
      p_user_id: userId,
    });

    if (error) {
      console.error('Error claiming pot:', error);

      // Handle specific errors
      if (error.message.includes('not unlocked')) {
        return res.status(400).json({ error: 'Pot not unlocked yet' });
      }

      return res.status(500).json({ error: error.message });
    }

    const result = data[0];

    // Check if already claimed (race condition)
    if (result.has_claimed_today && result.claimed_amount === 0) {
      return res.status(409).json({ error: 'Already claimed today' });
    }

    // Log successful claim
    console.log(`🍯 Pot claimed by ${userId}: +${result.claimed_amount} credits (total: ${result.total_credits})`);

    res.json(result);
  } catch (error) {
    console.error('Daily pot claim error:', error);
    res.status(500).json({ error: 'Failed to claim pot' });
  }
});

// ==========================================
// Optional: GET /daily-pot/leaderboard
// Top 10 users by total credits (for future)
// ==========================================
app.get('/daily-pot/leaderboard', async (req, res) => {
  try {
    const { data, error } = await supabase
      .from('daily_pots')
      .select('user_id, total_credits')
      .order('total_credits', { ascending: false })
      .limit(10);

    if (error) {
      console.error('Error fetching leaderboard:', error);
      return res.status(500).json({ error: error.message });
    }

    res.json(data);
  } catch (error) {
    console.error('Leaderboard error:', error);
    res.status(500).json({ error: 'Failed to fetch leaderboard' });
  }
});

module.exports = {
  // Export if needed
};
