import type { NextConfig } from 'next';

const nextConfig: NextConfig = {
  // API 라우트는 /api 경로로 자동 설정됨
  async headers() {
    return [
      {
        source: '/api/:path*',
        headers: [
          {
            key: 'Cache-Control',
            value: 'no-store',
          },
        ],
      },
    ];
  },
};

export default nextConfig;