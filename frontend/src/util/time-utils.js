const timeAgo = (date) => {
  const now = new Date();
  const postedDate = new Date(date);
  const seconds = Math.floor((now - postedDate) / 1000);
  const minutes = Math.floor(seconds / 60);
  const hours = Math.floor(minutes / 60);
  const days = Math.floor(hours / 24);

  if (days > 0) return `${days}일 전`;
  if (hours > 0) return `${hours}시간 전`;
  if (minutes > 0) return `${minutes}분 전`;
  return '방금 전';
};

const dateFromString = (dateString) => {
  // 문자열을 Date 객체로 변환
  const date = new Date(dateString);

  // 연도, 월, 일을 추출
  const year = date.getFullYear();
  const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 월은 0부터 시작하므로 1을 더하고 2자리로 맞춤
  const day = date.getDate().toString().padStart(2, '0'); // 일자를 2자리로 맞춤
  
  // 원하는 형식으로 포맷팅
  return `${year}.${month}.${day}`;
}

export { timeAgo, dateFromString };
