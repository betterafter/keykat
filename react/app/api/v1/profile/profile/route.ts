import { NextResponse } from 'next/server';

export async function GET() {
    try {
        const data = {
            "name": "keykat",
            "tel": "010-6850-1740",
            "email": "koin20000@gmail.com",
            "profile_url": "https://avatars.githubusercontent.com/u/26290540?v=4",
            "introduce": "안녕하세요.",
            "sns": [
                {
                    "name": "instagram",
                    "url": "https://www.instagram.com/keykat77/",
                    "icon": ""
                }
            ]
        };

        return NextResponse.json(data);
    } catch (error) {
        return NextResponse.json(
            { error: 'Failed to fetch posts' },
            { status: 500 }
        );
    }
}