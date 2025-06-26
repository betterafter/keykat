

import { NextResponse } from "next/server";

export async function GET() {
    return NextResponse.json({ message: "ok" });
}

// import { query } from '@/utils/firebase-admin';
// import { NextResponse } from 'next/server';

// export async function GET() {
//     try {
//         const tech = await query<any>('tech')
//         return NextResponse.json(tech[0].tech);
//     } catch (error) {
//         return NextResponse.json(
//             { error: 'Failed to fetch posts' },
//             { status: 500 }
//         );
//     }
// }