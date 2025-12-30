# CountDownApp

A simple countdown application built with Java Swing. This is just a cute countdown timer.

Initially developed for 2026 New Year countdown, but can be used for any countdown purpose.

## Author

William Wu, under the MIT License. See LICENSE file for details.

## Flags

The application supports the following command-line flags to customize the countdown target:

| Flag            | Description                                      | Example                |
|-----------------|--------------------------------------------------|------------------------|
| -h, --hours     | Set target hour (0-23)                           | `-h 23`                |
| -m, --minutes   | Set target minute (0-59)                         | `-m 59`                |
| -s, --seconds   | Set target second (0-59)                         | `-s 30`                |
| -t, --time      | Set target time (HH:MM:SS)                       | `-t 23:59:59`          |
| -n, --midnight  | Set target time to midnight (00:00:00)           | `-n`                   |
| -y, --years     | Set target year (YYYY)                           | `-y 2026`              |
| -mo, --months   | Set target month (1-12)                          | `-mo 12`               |
| -d, --days      | Set target day (1-31)                            | `-d 31`                |
| -dt, --date     | Set target date (YYYY-MM-DD)                     | `-dt 2026-01-01`       |
| -a, --annotate  | Add annotation to window title                   | `-a "New Year"`        |

You can combine date and time flags to set a specific countdown target. If no flags are provided, the app displays the current time.

## Keys

The application supports the following keyboard shortcuts:

| Keys                | Action                                   |
|---------------------|------------------------------------------|
| Ctrl + F            | Toggle fullscreen/maximize window        |
| Ctrl + S            | Toggle window resizability (freeze size) |

Press the above key combinations while the countdown window is focused to use these features.
