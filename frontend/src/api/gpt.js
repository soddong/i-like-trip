const API_KEY = 'sk-proj-GNX9uIgvK9DrQwby0HgkT3BlbkFJJk4BhRO58Rw45nmVGZBA';
const API_URL = 'https://api.openai.com/v1/chat/completions';

async function getChatgpt() {
    const { localAxiosInstance } = await import("@/util/http-commons");
    const local = localAxiosInstance;
    try {
        const requestBody = {
            model: 'gpt-3.5-turbo',
            max_tokens: 1000,
            temperature: 1,
            messages: [{
                role: 'user',
                content: '너가 분석하기에 3개월간(2024.2~2024.5) 가장 인기있었던 국내 여행지 top5 를 알려줘. 1. 2. 3. 4. 5. 지역만 나열해줘'
            }]
        };

        const response = await local.post(API_URL, requestBody, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${API_KEY}`
            }
        });

        if (response.status === 200 && response.data && response.data.choices && response.data.choices.length > 0) {
            const content = response.data.choices[0].message.content;
            return content;
        } else {
            console.log('Invalid or no data received:', response.data);
            return 'No response from OpenAI API.';
        }
    } catch (error) {
        console.error('Error sending request:', error);
        console.error('Response status:', error.response ? error.response.status : 'No response');
        console.error('Response data:', error.response ? error.response.data : 'No response data');
        return `An error occurred: ${error.message}`;
    }
}


export { getChatgpt };
