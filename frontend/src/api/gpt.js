import { localAxiosInstance } from "@/util/http-commons";

const local = localAxiosInstance;

const API_KEY = 'sk-proj-j42dIXqfLndSc62NTyrVT3BlbkFJFKLiG40LIPOkK5Em76qe';
const API_URL = 'https://api.openai.com/v1/chat/completions';

function getChatgpt() {
    try {

        // JSON 요청 본문 생성
        const requestBody = {
            model: 'gpt-3.5-turbo',
            max_tokens: 100,
            temperature: 1,
            messages: [
                {
                    role: 'user',
                    content: '3개월간(24년1월 ~ 24년4월) 가장 인기있었던 국내 여행지 top5를 알려줘'
                }
            ]
        };

        // HTTP 요청 생성 및 보내기
        const response = local.post(API_URL, requestBody, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${API_KEY}`
            }
        });

        if (response.status === 200) {
            // 응답 처리
            const choices = response.data.choices;
            if (Array.isArray(choices) && choices.length > 0) {
                const content = choices[0].message.content;
                return content; // 성공적인 응답 반환
            } else {
                return 'No response from OpenAI API.';
            }
        } else {
            return `Error: ${response.status} - ${response.data}`;
        }
    } catch (error) {
        console.error(error);
        return 'An error occurred.';
    }
}

export { getChatgpt };
