import { NextRequest, NextResponse } from "next/server";

export async function POST(req: NextRequest) {
  console.log("telegram webhook");
  const body = await req.json();

  if (body?.chat_member) {
    const { from, chat, old_chat_member, new_chat_member } = body.chat_member;

    const isJoinEvent =
      old_chat_member.status === "left" &&
      ["member", "administrator", "creator"].includes(new_chat_member.status);

    if (isJoinEvent) {
      const userId = from.id;
      const channelId = chat.id;
      const username = from.username;

      console.log(`✅ User @${username} (${userId}) joined ${channelId}`);

      // TODO: 포인트 지급 로직 실행
    }
  }

  return NextResponse.json({ message: "ok" });
}
