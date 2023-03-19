function popUpSearchBar() {
    var popup = document.getElementById("underBar");
    var displayVal = popup?.style.display;
    if (displayVal == "none") displayVal = "block";
    else displayVal = "none";
    if(popup != null) popup.style.display = displayVal;
}