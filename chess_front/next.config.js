/** @type {import('next').NextConfig} */

const nextConfig = {
    output: 'export',
    basePath: '/chess',
    images: {
        unoptimized: true,
        minimumCacheTTL: 86400,
    }
}

module.exports = nextConfig
