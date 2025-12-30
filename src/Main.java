import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Try parse time target from args
        java.time.LocalDateTime targetTime = null;
        String titleAddition = null;
        boolean relative = false;
        if (args.length > 0) {
            // Flags: -h, --hours, -m, --minutes, -s, --seconds, -t, --time (HH:MM:SS), -n, --midnight
            // Flags: -y, --years, -mo, --months, -d, --days, -dt, --date (YYYY-MM-DD), -a, --annotate
            // Flags: -r, --relative
            int hours = -1, minutes = -1, seconds = -1;
            int years = -1, months = -1, days = -1;
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-h", "--hours" -> {
                        if (i + 1 < args.length) {
                            hours = Integer.parseInt(args[i + 1]);
                            i++;
                        } else {
                            System.err.println("Error: Missing value for hours.");
                            return;
                        }
                    }
                    case "-m", "--minutes" -> {
                        if (i + 1 < args.length) {
                            minutes = Integer.parseInt(args[i + 1]);
                            i++;
                        } else {
                            System.err.println("Error: Missing value for minutes.");
                            return;
                        }
                    }
                    case "-s", "--seconds" -> {
                        if (i + 1 < args.length) {
                            seconds = Integer.parseInt(args[i + 1]);
                            i++;
                        } else {
                            System.err.println("Error: Missing value for seconds.");
                            return;
                        }
                    }
                    case "-n", "--midnight" -> {
                        hours = 0;
                        minutes = 0;
                        seconds = 0;
                    }
                    case "-y", "--years" -> {
                        if (i + 1 < args.length) {
                            years = Integer.parseInt(args[i + 1]);
                            i++;
                        } else {
                            System.err.println("Error: Missing value for years.");
                            return;
                        }
                    }
                    case "-d", "--days" -> {
                        if (i + 1 < args.length) {
                            days = Integer.parseInt(args[i + 1]);
                            i++;
                        } else {
                            System.err.println("Error: Missing value for days.");
                            return;
                        }
                    }
                    case "-mo", "--months" -> {
                        if (i + 1 < args.length) {
                            months = Integer.parseInt(args[i + 1]);
                            i++;
                        } else {
                            System.err.println("Error: Missing value for months.");
                            return;
                        }
                    }
                    case "-t", "--time" -> {
                        if (i + 1 < args.length) {
                            String[] timeParts = args[i + 1].split(":");
                            if (timeParts.length == 3) {
                                hours = Integer.parseInt(timeParts[0]);
                                minutes = Integer.parseInt(timeParts[1]);
                                seconds = Integer.parseInt(timeParts[2]);
                            } else {
                                System.err.println("Error: Time format should be HH:MM:SS.");
                                return;
                            }
                            i++;
                        } else {
                            System.err.println("Error: Missing value for time.");
                            return;
                        }
                    }
                    case "-dt", "--date" -> {
                        if (i + 1 < args.length) {
                            String[] dateParts = args[i + 1].split("-");
                            if (dateParts.length == 3) {
                                years = Integer.parseInt(dateParts[0]);
                                months = Integer.parseInt(dateParts[1]);
                                days = Integer.parseInt(dateParts[2]);
                            } else {
                                System.err.println("Error: Date format should be YYYY-MM-DD.");
                                return;
                            }
                            i++;
                        } else {
                            System.err.println("Error: Missing value for date.");
                            return;
                        }
                    }
                    case "-a", "--annotate" -> {
                        if (i + 1 < args.length) {
                            titleAddition = args[i + 1];
                            i++;
                        } else {
                            System.err.println("Error: Missing value for annotate.");
                            return;
                        }
                    }
                    case "-r", "--relative" -> {
                        relative = true;
                    }
                    default -> {
                        System.err.println("Error: Unknown argument " + args[i]);
                        return;
                    }
                }
            }
            // If all -1, set targetTime to null (show current time)
            if (hours == -1 && minutes == -1 && seconds == -1 && years == -1 && months == -1 && days == -1) {
                targetTime = null;
            }
            // Relative time calculation
            else if (relative) {
                var nowDateTime = java.time.LocalDateTime.now();
                targetTime = nowDateTime;
                if (years != -1) {
                    targetTime = targetTime.plusYears(years);
                }
                if (months != -1) {
                    targetTime = targetTime.plusMonths(months);
                }
                if (days != -1) {
                    targetTime = targetTime.plusDays(days);
                }
                if (hours != -1) {
                    targetTime = targetTime.plusHours(hours);
                }
                if (minutes != -1) {
                    targetTime = targetTime.plusMinutes(minutes);
                }
                if (seconds != -1) {
                    targetTime = targetTime.plusSeconds(seconds);
                }
            }
            // Calculate target time
            else {
                var nowDateTime = java.time.LocalDateTime.now();
                var targetDateTime = nowDateTime;
                if (years != -1 || months != -1 || days != -1) {
                    int targetYear = (years != -1) ? years : nowDateTime.getYear();
                    int targetMonth = (months != -1) ? months : nowDateTime.getMonthValue();
                    int targetDay = (days != -1) ? days : nowDateTime.getDayOfMonth();
                    targetDateTime = targetDateTime.withYear(targetYear).withMonth(targetMonth).withDayOfMonth(targetDay);
                }
                if (hours != -1 || minutes != -1 || seconds != -1) {
                    int targetHour = (hours != -1) ? hours : nowDateTime.getHour();
                    int targetMinute = (minutes != -1) ? minutes : nowDateTime.getMinute();
                    int targetSecond = (seconds != -1) ? seconds : nowDateTime.getSecond();
                    targetDateTime = targetDateTime.withHour(targetHour).withMinute(targetMinute).withSecond(targetSecond);
                }
                // If targetDateTime is before now, and any of years/months/days were specified, do not adjust
                if (targetDateTime.isBefore(nowDateTime) && (years != -1 || months != -1 || days != -1)) {
                    // Do nothing
                }
                // Else if targetDateTime is before now, add one day
                else if (targetDateTime.isBefore(nowDateTime)) {
                    targetDateTime = targetDateTime.plusDays(1);
                }
                targetTime = targetDateTime;
            }
        }
        // System.out.println("Target Time: " + (targetTime != null ? targetTime.toString() : "Current Time Display"));
        // UI Setup
        var backgroundColor = new Color(39, 39, 40);
        var foregroundColor = new Color(154, 154, 154);
        var minDimension = new Dimension(400, 100);
        var iconImage = fetchIconImage();
        var title = "COUNTDOWN" + (titleAddition != null ? " - " + titleAddition : "");
        var mainFrame = new JFrame(title){};
        var mainPanel = new JPanel();
        var mainLabel = new JLabel();
        var fps = 120;
        mainLabel.setFont(generateFont(100));
        mainLabel.setForeground(foregroundColor);
        mainLabel.setVerticalTextPosition(JLabel.CENTER);
        mainLabel.setHorizontalTextPosition(JLabel.CENTER);
        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        mainLabel.setVerticalAlignment(JLabel.CENTER);
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(mainLabel);
        mainPanel.setLayout(new GridBagLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setBackground(backgroundColor);
        mainPanel.setBackground(backgroundColor);
        mainFrame.setSize(minDimension);
        mainFrame.setMinimumSize(minDimension);
        if (iconImage != null) {
            mainFrame.setIconImage(iconImage);
            Taskbar.getTaskbar().setIconImage(iconImage);
        }
        mainFrame.setVisible(true);
        mainFrame.add(mainPanel);
        var sizeRecalculationListener = new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                var newSize = Math.min(mainFrame.getWidth() * 0.20, mainFrame.getHeight()) * 0.6;
                mainLabel.setFont(generateFont((int) newSize));
            }
        };
        var maxSizeWhenPressControlFListener = new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == java.awt.event.KeyEvent.VK_F) {
                    if (mainFrame.getExtendedState() == JFrame.NORMAL) {
                        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    } else {
                        mainFrame.setExtendedState(JFrame.NORMAL);
                    }
                }
            }
        };
        var freezeSizeWhenPressControlSListener = new java.awt.event.KeyAdapter() {
            private boolean isFrozen = false;
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == java.awt.event.KeyEvent.VK_S) {
                    if (!isFrozen) {
                        mainFrame.setResizable(false);
                        isFrozen = true;
                    } else {
                        mainFrame.setResizable(true);
                        isFrozen = false;
                    }
                }
            }
        };
        mainFrame.addKeyListener(freezeSizeWhenPressControlSListener);
        mainFrame.addKeyListener(maxSizeWhenPressControlFListener);
        mainFrame.addComponentListener(sizeRecalculationListener);
        // Initial size calculation
        sizeRecalculationListener.componentResized(null);
        // Event loop
        while (true) {
            var currentTime = getFormattedTimeRemaining(targetTime);
            mainLabel.setText(currentTime);
            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static Image fetchIconImage(){
        var path = "icon.png"; // Replace with your icon file path, or if it is nullable to use default java icon
        var url = Main.class.getResource(path);
        return url != null ? new ImageIcon(url).getImage() : null;
    }
    private static Font generateFont(int size) {
        var font = "Sono"; // Replace with your desired font name
        return new Font(font, Font.BOLD, size);
    }
    private static String getFormattedTimeNow(){
        var now = java.time.LocalTime.now();
        return String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());
    }
    private static String getFormattedTimeRemaining(java.time.LocalDateTime targetTime){
        if (targetTime == null) {
            return getFormattedTimeNow();
        }
        var now = java.time.LocalDateTime.now();
        var duration = java.time.Duration.between(now, targetTime);
        long totalSeconds = duration.getSeconds();
        if (totalSeconds < 0) totalSeconds = 0;
        long days = totalSeconds / 86400; totalSeconds %= 86400;
        long hours = totalSeconds / 3600; totalSeconds %= 3600;
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        if (days == 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else return String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds);
    }
}