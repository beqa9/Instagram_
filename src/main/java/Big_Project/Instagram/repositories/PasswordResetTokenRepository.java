package Big_Project.Instagram.repositories;

import Big_Project.Instagram.entities.PasswordResetToken;

import java.util.Optional;

public interface PasswordResetTokenRepository
        extends BaseRepository<PasswordResetToken> {

    Optional<PasswordResetToken> findByToken(String token);
}