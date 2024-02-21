document.addEventListener('DOMContentLoaded', function() {
    const imgLinks = document.querySelectorAll('.img-item a');

    imgLinks.forEach((imgLink) => {
        imgLink.addEventListener('click', (event) => {
            event.preventDefault();
            const imgId = imgLink.getAttribute('data-id');
            slideImage(imgId);
        });
    });

    function slideImage(imgId) {
        const displayWidth = document.querySelector('.img-showcase img:first-child').clientWidth;
        document.querySelector('.img-showcase').style.transform = `translateX(${- (imgId - 1) * displayWidth}px)`;
    }

    window.addEventListener('resize', function() {
        const currentImgId = document.querySelector('.img-showcase img:first-child').getAttribute('data-id');
        slideImage(currentImgId);
    });
});
function generateStarRating(rating) {
    const fullStar = '<i class="fas fa-star"></i>';
    const halfStar = '<i class="fas fa-star-half-alt"></i>';
    const emptyStar = '<i class="far fa-star"></i>';
    
    let starsHtml = '';
    let fullStars = Math.floor(rating);
    let hasHalfStar = rating % 1 !== 0;

    for (let i = 0; i < fullStars; i++) {
        starsHtml += fullStar;
    }

    if (hasHalfStar) {
        starsHtml += halfStar;
        fullStars++; // Add 1 to count the half star
    }

    // Fill remaining stars with empty stars
    const remainingStars = 5 - fullStars;
    for (let i = 0; i < remainingStars; i++) {
        starsHtml += emptyStar;
    }

    return starsHtml;
}

// 예시 평점을 0.0부터 5.0까지 다양하게 테스트
const ratings = [0.0, 1.5, 2.8, 3.0, 4.7, 5.0];

ratings.forEach(rating => {
    const starsHtml = generateStarRating(rating);
    const cnt = 100; // 리뷰 개수
    const ratingContainer = document.querySelector('.product-rating');
    ratingContainer.innerHTML = starsHtml + `(${rating})(${cnt})`;
});