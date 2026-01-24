# GreenGains Analytics Dashboard

Modern, professional analytics dashboard for GreenGains Smart Cities platform.

## Stack

- **Next.js 14** - React framework with App Router
- **TypeScript** - Type safety
- **Tailwind CSS** - Utility-first styling
- **shadcn/ui** - Beautiful, accessible components (modular - you own the code!)

## Getting Started

1. Install dependencies:
```bash
npm install
```

2. Run development server:
```bash
npm run dev
```

3. Open [http://localhost:3000](http://localhost:3000)

## Features

- ✅ Clean, professional design matching shadcn/ui aesthetic
- ✅ Fully responsive
- ✅ Dark mode (default)
- ✅ Real-time data display
- 🚧 Barometer data integration (coming soon)
- 🚧 API integration with greengains.onrender.com
- 🚧 WebSocket real-time updates

## Project Structure

```
dashboard-web/
├── app/                 # Next.js App Router
│   ├── layout.tsx      # Root layout
│   ├── page.tsx        # Dashboard page
│   └── globals.css     # Global styles
├── components/         # React components (add here)
├── lib/               # Utilities
└── public/            # Static assets
```

## Next Steps

1. Add shadcn/ui components as needed
2. Create API client for backend
3. Build data fetching hooks
4. Add real-time updates
5. Deploy to Vercel
