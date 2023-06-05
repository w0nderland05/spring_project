package org.study.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.study.commons.UserUtils;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {
    private final UserUtils userUtils;

    @Override
    public Optional<String> getCurrentAuditor() {
        String userEmail = null;
        if (userUtils.isLogin()) {
            userEmail = userUtils.getUser().getUserEmail();
        }

        return Optional.ofNullable(userEmail);

    }
}
