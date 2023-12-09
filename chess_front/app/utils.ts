
export function getHost(): string {
    return typeof window === 'undefined' ?
        'https://overwave.dev' :
        localStorage.getItem("local") ? 'http://localhost:8081' : 'https://overwave.dev';
}