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