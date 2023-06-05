window.addEventListener("DOMContentLoaded", function() {
    // 스와이퍼 로드
    const swiper = new Swiper(".swiper.banner");

    // 탭 클릭시 이벤트 처리 S
    const tabEls = document.querySelectorAll(".tabs-wrap .tab");
    const tabContentEls = document.querySelectorAll(".tab_content article");
    for (const el of tabEls) {
        el.addEventListener("click", function() {
            for (const el2 of tabEls) {
                el2.classList.remove("on");
            }

            this.classList.add("on");

            const tab = this.dataset.tab;

            for (const el2 of tabContentEls) {
                el2.classList.remove("dn");
                el2.classList.add("dn");
            }

            const tabContent = document.querySelector(`.tab_content article.${tab}`);
            tabContent.classList.remove("dn");

        });
    }
    // 탭 클릭시 이벤트 처리 E
});