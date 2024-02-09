import { LoginI18n, LoginOverlay, LoginOverlayElement } from '@hilla/react-components/LoginOverlay.js';
import { useState } from 'react';
import { useAuth } from 'Frontend/util/auth.js';
import { useNavigate } from 'react-router-dom';

const loginI18n: LoginI18n = {
    ...new LoginOverlayElement().i18n,
    header: { title: 'Hilla with Keycloak', description: 'Resource Owner Password Flow' },
  };

export default function LoginView() {
  const { login } = useAuth();
  const [hasError, setError] = useState<boolean>();
  const navigate = useNavigate();
  
  return (
    <LoginOverlay
      opened
      error={hasError}
      noForgotPassword
      i18n={loginI18n}
      onLogin={async ({ detail: { username, password } }) => {
        const { error } = await login(username, password);

        if (error) {
          setError(true);
        } else {
          navigate('/');
        }
      }}
    />
  );
}