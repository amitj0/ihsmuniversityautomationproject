package com.ihsm.university.utilities;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.*;

public class EmailUtils {

    public static String getLatestReport(String reportDir) {
        File dir = new File(reportDir);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".html"));

        if (files == null || files.length == 0) {
            throw new RuntimeException("No report found in: " + reportDir);
        }

        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0].getAbsolutePath();
    }

    public static void sendMail(String reportPath) {

        final String from = ConfigReader.get("email.from");
        final String password = ConfigReader.get("email.password");
        final String to = ConfigReader.get("email.to");

        if (from == null || password == null || to == null) {
            throw new RuntimeException("Email config missing in config.properties");
        }

        // ---------------- RESULT STATUS ----------------
        String status = TestResultSummary.getStatus();

        // OPTIONAL: Skip email if all tests passed
        // if ("PASSED".equals(status)) return;

        String subject;
        String color;
        String icon;

        switch (status) {
            case "FAILED":
                subject = "❌ IHSM University – Automation FAILED";
                color = "#E74C3C";
                icon = "❌";
                break;
            case "SKIPPED":
                subject = "⚠️ IHSM University – Automation SKIPPED";
                color = "#F39C12";
                icon = "⚠️";
                break;
            default:
                subject = "✅ IHSM University – Automation PASSED";
                color = "#27AE60";
                icon = "✅";
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            // ---------------- EMAIL BODY ----------------
            MimeBodyPart body = new MimeBodyPart();

            String htmlBody =
                    "<html><body style='font-family:Arial,sans-serif;'>" +

                    "<h2 style='color:" + color + ";'>" + icon +
                    " Automation Test Execution " + status + "</h2>" +

                    "<p>Hello Team,</p>" +

                    "<table style='border-collapse:collapse; width:60%; margin-top:15px;'>" +

                    "<tr><td style='border:1px solid #ccc; padding:8px;'><b>Project</b></td>" +
                    "<td style='border:1px solid #ccc; padding:8px;'>IHSM University</td></tr>" +

                    "<tr><td style='border:1px solid #ccc; padding:8px;'><b>Status</b></td>" +
                    "<td style='border:1px solid #ccc; padding:8px; color:" + color + ";'><b>" +
                    status + "</b></td></tr>" +

                    "<tr><td style='border:1px solid #ccc; padding:8px;'>Passed</td>" +
                    "<td style='border:1px solid #ccc; padding:8px; color:green;'>" +
                    TestResultSummary.passed + "</td></tr>" +

                    "<tr><td style='border:1px solid #ccc; padding:8px;'>Failed</td>" +
                    "<td style='border:1px solid #ccc; padding:8px; color:red;'>" +
                    TestResultSummary.failed + "</td></tr>" +

                    "<tr><td style='border:1px solid #ccc; padding:8px;'>Skipped</td>" +
                    "<td style='border:1px solid #ccc; padding:8px; color:orange;'>" +
                    TestResultSummary.skipped + "</td></tr>" +

                    "</table>" +

                    "<p style='margin-top:20px;'>Detailed Extent Report is attached.</p>" +

                    "<p><b>Regards,</b><br>Automation Team</p>" +

                    "</body></html>";

            body.setContent(htmlBody, "text/html; charset=utf-8");

            // ---------------- ATTACHMENT ----------------
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(new File(reportPath));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(body);
            multipart.addBodyPart(attachment);

            message.setContent(multipart);
            Transport.send(message);

            System.out.println("✅ Email sent successfully");

        } catch (Exception e) {
            System.err.println("❌ Failed to send email");
            e.printStackTrace();
        }
    }
}
