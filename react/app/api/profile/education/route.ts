import { NextResponse } from 'next/server';

export async function GET() {
    try {
        const data = `{
            "education": [
                {
                "name": "네이버 부스트캠프 웹-모바일 6기",
                "where": "네이버 커넥트재단",
                "start_at": "2021.07.14",
                "end_at": "2021.12.20",
                "content": "어떤 공부를 했나요?"
                },
                {
                "name": "건국대학교",
                "where": "건국대학교",
                "start_at": "2016.03.02",
                "end_at": "2023.02.20",
                "content": "어떤 공부를 했나요?"
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