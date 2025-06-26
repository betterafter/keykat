import { NextResponse } from "next/server";

export async function GET() {
    return NextResponse.json({ message: "ok" });
}

// import { NextResponse } from 'next/server';
// import { query } from '@/utils/firebase-admin';

// export async function GET() {
//     try {
//         const education = await query<any>('education')
//         return NextResponse.json(education[0].education);
//     } catch (error) {
//         return NextResponse.json(
//             { error: 'Failed to fetch posts' },
//             { status: 500 }
//         );
//     }
// }