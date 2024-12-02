import { query } from '@/utils/firebase-admin'

// 프로필 데이터 가져오기
export async function GET(request: Request) {
    try {
        const profile = await query<any>('profile')
        return Response.json(profile[0])
    } catch (error) {
        return Response.json({ success: false, error: 'Failed to fetch profiles' }, { status: 500 })
    }
}