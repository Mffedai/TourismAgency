package core;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void setTheme() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }

    public static void showMsg(String str) {
        optionPaneTR();
        String msg;
        String title;
        switch (str) {
            case "fill" -> {
                msg = "Please fill the all field.";
                title = "ERROR!";
            }
            case "done" -> {
                msg = "Successful !";
                title = "Result";
            }
            case "notFound" -> {
                msg = "User did not found !";
                title = "not Found";
            }
            case "error" -> {
                msg = "Wrong transaction!";
                title = "ERROR!";
            }
            default -> {
                msg = str;
                title = "Message";
            }
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str) {
        optionPaneTR();
        String msg;
        if (str.equals("sure")) {
            msg = "Are you sure ? ";
        } else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Sure ?", JOptionPane.YES_NO_OPTION) == 0;
    }

    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) {
                return true;
            }
        }
        return false;
    }

    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }

    public static void optionPaneTR() {
        UIManager.put("OptionPane.okButtonText", "Okey");
        UIManager.put("OptionPane.yesButtonText", "Yes");
        UIManager.put("OptionPane.noButtonText", "No");
    }
}
