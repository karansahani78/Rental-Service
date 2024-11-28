// Function to check if an element is in the viewport
function isInViewport(element) {
    const rect = element.getBoundingClientRect();
    return rect.top < window.innerHeight && rect.bottom > 0;
}

// Function to add slide-in animation
function handleScrollAnimation() {
    const serviceBoxes = document.querySelectorAll('.service-box');
    serviceBoxes.forEach(box => {
        if (isInViewport(box)) {
            if (box.classList.contains('first-box') || box.classList.contains('third-box')) {
                box.classList.add('slide-in-left');
            } else if (box.classList.contains('second-box')) {
                box.classList.add('slide-in-right');
            }
        }
    });
}

// Run the animation check on page load and scroll
window.addEventListener('load', handleScrollAnimation);
window.addEventListener('scroll', handleScrollAnimation);
