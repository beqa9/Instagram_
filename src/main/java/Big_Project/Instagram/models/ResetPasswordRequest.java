package Big_Project.Instagram.models;

public record ResetPasswordRequest(
        String token,
        String newPassword
) {}