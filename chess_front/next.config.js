/** @type {import('next').NextConfig} */

const nextConfig = {
    output: 'export',
    basePath: '/chess',
    images: {
        unoptimized: true,
        minimumCacheTTL: 60,
    }
}

module.exports = nextConfig
