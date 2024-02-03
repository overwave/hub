
export function getHost(): string {
    return typeof window === 'undefined' ?
        'https://overwave.dev' :
        localStorage.getItem("local") ? 'http://localhost:8081' : 'https://overwave.dev';
}

export function isLoggedIn(): boolean {
    return typeof window === 'undefined' ? false : localStorage.getItem("loggedIn") === "true";
}

export function setLoggedIn(loggedIn: boolean) {
    if (typeof window !== 'undefined') {
        localStorage.setItem("loggedIn", String(loggedIn));
    }
}