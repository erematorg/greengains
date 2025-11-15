# Test API key rotation
Write-Host "🧪 Testing API Key Rotation..." -ForegroundColor Cyan
Write-Host ""

$backend = "https://greengains.onrender.com"
$oldKey = "Vb9kS3tP0xQ7fY2L"
$newKey = "Xk9mW2vN8qL5pR3tY7jH6bF4"

# Test 1: Old key should FAIL
Write-Host "1️⃣  Testing OLD key (should be rejected)..." -ForegroundColor Yellow
try {
    $headers = @{
        "X-API-Key" = $oldKey
        "Content-Type" = "application/json"
    }
    $body = @{
        device_id = "test-device"
        timestamp = [DateTimeOffset]::Now.ToUnixTimeMilliseconds()
        batch = @()
    } | ConvertTo-Json

    $response = Invoke-WebRequest -Uri "$backend/upload" -Method POST -Headers $headers -Body $body -ErrorAction Stop
    Write-Host "   ❌ FAIL: Old key still works! (Status: $($response.StatusCode))" -ForegroundColor Red
} catch {
    if ($_.Exception.Response.StatusCode -eq 401) {
        Write-Host "   ✅ PASS: Old key rejected (401 Unauthorized)" -ForegroundColor Green
    } else {
        Write-Host "   ⚠️  Unexpected error: $($_.Exception.Message)" -ForegroundColor Yellow
    }
}

Write-Host ""

# Test 2: New key should WORK
Write-Host "2️⃣  Testing NEW key (should work)..." -ForegroundColor Yellow
try {
    $headers = @{
        "X-API-Key" = $newKey
        "Content-Type" = "application/json"
    }
    $body = @{
        device_id = "test-device"
        timestamp = [DateTimeOffset]::Now.ToUnixTimeMilliseconds()
        batch = @()
    } | ConvertTo-Json

    $response = Invoke-WebRequest -Uri "$backend/upload" -Method POST -Headers $headers -Body $body -ErrorAction Stop
    Write-Host "   ✅ PASS: New key works! (Status: $($response.StatusCode))" -ForegroundColor Green
} catch {
    if ($_.Exception.Response.StatusCode -eq 401) {
        Write-Host "   ❌ FAIL: New key rejected! Did you update Render?" -ForegroundColor Red
    } else {
        Write-Host "   ⚠️  Error: $($_.Exception.Message)" -ForegroundColor Yellow
    }
}

Write-Host ""
Write-Host "🏁 Test Complete!" -ForegroundColor Cyan
