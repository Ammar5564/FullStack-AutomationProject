import { createContext, useContext, useState } from "react";
import axios from "axios";

// إنشاء Context
export const AuthContext = createContext();

// Hook للوصول للـContext في أي صفحة
export const useAuth = () => useContext(AuthContext);

function AuthProvider({ children }) {

    const [isAuthenticated, setAuthenticated] = useState(false);
    const [username, setUsername] = useState(null);

    // Login function تستخدم الـAPI
    async function login(usernameInput, passwordInput) {
        try {
            const response = await axios.post('http://localhost:8080/Auth/login', {
                username: usernameInput,
                password: passwordInput
            });

            if (response.status === 200) {
                setAuthenticated(true);
                setUsername(usernameInput);
                return true;
            }
        } catch (error) {
            setAuthenticated(false);
            setUsername(null);
            return false;
        }
    }

    // Logout
    function logout() {
        setAuthenticated(false);
        setUsername(null);
    }

    return (
        <AuthContext.Provider value={{ isAuthenticated, login, logout, username }}>
            {children}
        </AuthContext.Provider>
    );
}

export default AuthProvider;
