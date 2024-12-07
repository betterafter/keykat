import { NextResponse } from 'next/server';
import { query } from '@/utils/firebase-admin';

export async function GET() {
    try {
        const career = await query<any>('career')
        const careerDetail = await query<any>('career_detail')

        career[0].career.forEach((item: any) => {
            item.career_detail = []
        });

        careerDetail[0].career_detail.forEach((item: any) => {
            career[0].career.find(
                (career: any) => career.name == item.company_name
            )?.career_detail.push(item)
        })

        return NextResponse.json({
            career: career[0].career
        });
    } catch (error) {
        console.log(error)
        return NextResponse.json(
            { error: 'Failed to fetch posts' },
            { status: 500 }
        );
    }
}