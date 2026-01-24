# Screenshots for Design Work

This folder contains screenshots for design work and Figma prototyping.

## Structure

- `app/` - Mobile app screenshots
- `website/` - Website screenshots

## App Screenshots Checklist

### Onboarding Flow
- [ ] 01-onboarding-welcome.png
- [ ] 02-onboarding-features.png
- [ ] 03-onboarding-privacy.png
- [ ] 04-onboarding-signin.png

### Main Screens
- [ ] 05-home-screen.png
- [ ] 06-profile-screen.png
- [ ] 07-settings-screen.png
- [ ] 08-map-view.png

### Features
- [ ] 09-daily-pot-unclaimed.png
- [ ] 10-daily-pot-claimed.png
- [ ] 11-settings-language-french.png
- [ ] 12-settings-theme-dark.png

## Website Screenshots Checklist

- [ ] landing-page.png
- [ ] (Add more as needed)

## How to Capture

### App Screenshots
Run the capture script:
```powershell
.\capture-screenshots.ps1
```

Then navigate to each screen on your phone and press the corresponding number.

### Website Screenshots
Use browser screenshot tools or:
```powershell
# Manual capture and save to screenshots/website/
```

## Usage

Once captured, send screenshots to ChatGPT organized by:
- `app/` folder → For mobile app design work
- `website/` folder → For website design work

ChatGPT can then use these to create Figma designs, mockups, or improvements.
