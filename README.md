# Calendar App – Java Swing

A Java desktop application built with Swing that displays a monthly calendar. The user picks a year and a month from drop-down lists, and the calendar grid updates automatically.

## Features

- Year selector covering 50 years before and after the current year
- Month selector (January to December)
- Calendar grid with weekday headers (Sun to Sat) and one button per day
- Correct start day for each month, with empty cells before the 1st
- Handles different month lengths and leap years using `java.time.YearMonth`
- Highlights today's date in light blue and bold when it is on screen
- Opens on the current year and month

## Requirements

- Java Development Kit (JDK) 8 or higher (Swing and `java.time` are included in the JDK)

## Getting Started

### Clone the repository

```bash
git clone https://github.com/<your-username>/<your-repo-name>.git
cd <your-repo-name>
```

### Compile

```bash
javac CalendarApp.java
```

### Run

```bash
java CalendarApp
```

## How to Use

1. Launch the application. It shows the current month with today highlighted.
2. Choose a different year or month from the drop-downs.
3. The calendar redraws immediately.

## How It Works

- The window uses a `BorderLayout`: the year and month controls sit at the top, and the calendar grid fills the center.
- The grid is a `GridLayout` with 7 columns. The first row holds the weekday names.
- For the selected month, the program finds which weekday the 1st falls on and adds that many empty labels, so day 1 lines up under the correct weekday. Weeks start on Sunday.
- `updateCalendar()` clears the grid, rebuilds it, and calls `revalidate()` and `repaint()`. It runs on startup and whenever either drop-down changes.

## Project Structure

```
.
├── CalendarApp.java
└── README.md
```

## Possible Improvements

- Remove the placeholder **New button** at the bottom of the window, which currently does nothing, or give it a real function
- Add click actions to the day buttons (for example, showing events or notes for that date)
- Add previous and next month buttons
- Add an option to start the week on Monday
- Rename the window title (`Calendar Year`), since the app shows a single month
