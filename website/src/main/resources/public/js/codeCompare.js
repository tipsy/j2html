document.addEventListener("click", function(e) {
    var classList = e.target.classList;
    var codeCompare = e.target.parentElement.parentElement;
    if (classList.contains("nowith-switch")) {
        codeCompare.classList.add("nowith");
        codeCompare.classList.remove("with");
    }
    if (classList.contains("with-switch")) {
        codeCompare.classList.add("with");
        codeCompare.classList.remove("nowith");
    }
});