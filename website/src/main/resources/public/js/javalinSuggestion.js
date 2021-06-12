(function () {
    var javalinSuggestion = document.querySelector("#javalin-suggestion");
    if (localStorage.getItem("javalin-suggestion-dismissed") !== "true") {
        javalinSuggestion.style.display = "block";
        javalinSuggestion.querySelector(".close").addEventListener("click", function () {
            javalinSuggestion.style.display = "none";
            localStorage.setItem("javalin-suggestion-dismissed", "true");
        });
    }
})();
