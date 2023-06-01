window.addEventListener("DOMContentLoaded", function() {
    const cateCdEls = document.querySelectorAll("#frmList input[name='cateCd']");
    for (const el of cateCdEls) {
        el.addEventListener("click", function() {
            frmList.submit();
        });
    }

});