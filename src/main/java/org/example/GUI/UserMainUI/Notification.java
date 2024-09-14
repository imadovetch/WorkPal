package org.example.GUI.UserMainUI;

import org.example.entities.User;
import org.example.services.implementations.EmailServiceImp;

public class Notification {

    // Send notification for successful reservation
    public void sendReservationConfirmation(int reservationId, String spaceName) {
        String context = "Your reservation for " + spaceName + " (Reservation ID: " + reservationId + ") has been confirmed.";
        String senderEmail = User.Main.getEmail();
        sendEmail(context, senderEmail);
    }

    // Send notification for reservation cancellation
    public void sendReservationCancellation(int reservationId, String spaceName) {
        String context = "Your reservation for " + spaceName + " (Reservation ID: " + reservationId + ") has been cancelled.";
        String senderEmail = User.Main.getEmail();
        sendEmail(context, senderEmail);
    }

    // Send notification for reservation reminder
    public void sendReservationReminder(int reservationId, String spaceName, String reservationDate) {
        String context = "This is a reminder for your reservation at " + spaceName + " on " + reservationDate + ". (Reservation ID: " + reservationId + ")";
        String senderEmail = User.Main.getEmail();
        sendEmail(context, senderEmail);
    }

    // Send notification for profile update
    public void sendProfileUpdateConfirmation() {
        String context = "Your profile information has been successfully updated.";
        String senderEmail = User.Main.getEmail();
        sendEmail(context, senderEmail);
    }

    // Send notification for password reset request
    public void sendPasswordResetNotification(String temporaryPassword) {
        String context = "You have requested to reset your password. Use this temporary password to login: " + temporaryPassword + ". Please change your password after logging in.";
        String senderEmail = User.Main.getEmail();
        sendEmail(context, senderEmail);
    }

    // Send notification for new membership subscription
    public void sendSubscriptionConfirmation(String planName) {
        String context = "You have successfully subscribed to the " + planName + " plan.";
        String senderEmail = User.Main.getEmail();
        sendEmail(context, senderEmail);
    }

    // Send notification for subscription renewal
    public void sendSubscriptionRenewalConfirmation(String planName) {
        String context = "Your subscription to the " + planName + " plan has been successfully renewed.";
        String senderEmail = User.Main.getEmail();
        sendEmail(context, senderEmail);
    }

    // Send notification for subscription cancellation
    public void sendSubscriptionCancellationConfirmation() {
        String context = "Your subscription has been cancelled successfully.";
        String senderEmail = User.Main.getEmail();
        sendEmail(context, senderEmail);
    }

    // Send notification for admin when a new reservation is made
    public void notifyAdminNewReservation(int reservationId, String userEmail, String spaceName) {
        String context = "A new reservation has been made by " + userEmail + " for " + spaceName + ". (Reservation ID: " + reservationId + ")";
        String senderEmail = "admin@example.com"; // Replace with actual admin email
        sendEmail(context, senderEmail);
    }

    // Helper method to send emails
    private void sendEmail(String context, String senderEmail) {
        new EmailServiceImp().main(context, senderEmail);
    }
}
