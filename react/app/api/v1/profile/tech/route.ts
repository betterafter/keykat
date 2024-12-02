import { NextResponse } from 'next/server';

export async function GET() {
    try {
        const data = `{
            "tech": [
                {
                    "name": "Flutter",
                    "icon": "",
                    "content": "플러터 개발"
                },
                {
                    "name": "Android",
                    "icon": "",
                    "content": "안드로이드 개발"
                },
                {
                    "name": "Next.js",
                    "icon": "",
                    "content": "웹 프론트엔드 개발"
                }
            ]
        }`;

        return NextResponse.json(data);
    } catch (error) {
        return NextResponse.json(
            { error: 'Failed to fetch posts' },
            { status: 500 }
        );
    }
}