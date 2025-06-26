import { NextResponse } from "next/server";

export async function GET() {
    return NextResponse.json({ message: "ok" });
}   

// import { NextResponse } from 'next/server';
// import { query } from '@/utils/firebase-admin';

// export async function GET() {
//     try {
//         const portfolio = await query<any>('portfolio')
//         console.log(portfolio[0])
//         return NextResponse.json({
//             project: portfolio[0].project,
//             career: portfolio[0].career,
//         });
//     } catch (error) {
//         console.log(error)
//         return NextResponse.json(
//             { error: 'Failed to fetch posts' },
//             { status: 500 }
//         );
//     }
// }