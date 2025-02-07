// Load header
fetch('header.html')
    .then(response => response.text())
    .then(data => {
        // Insert the header content
        document.getElementById('header-placeholder').innerHTML = data;

        // Dynamically set header image based on page type
        const pageType = document.body.dataset.page; // Get the page type from the <body> tag
        const headerElement = document.querySelector('.header');

        // Remove any existing banner or header image
        const existingHeaderImage = headerElement.querySelector('.header-image');
        const existingBannerImage = headerElement.querySelector('.banner-image');
        if (existingHeaderImage) existingHeaderImage.remove();
        if (existingBannerImage) existingBannerImage.remove();

        // Add the appropriate image for the current page
        if (pageType === "index") {
            // Landing page header image
            const headerImage = `
                <div class="header-image">
                    <div class="project-title">
                        <h1 style="font-size: 85px !important; color: rgb(230, 215, 231);">RentEasy</h1>
                    </div>
                    <div class="overlay">
                        <a class="btn" href="#">Talk to Us</a>
                    </div>
                </div>`;
            headerElement.insertAdjacentHTML('beforeend', headerImage); // Add the landing page header image
        } else {
            // Default header image for other pages
            const bannerImage = `
                <div class="banner-image" id="banner-image-section">
                    <div class="gen-page-title">
                        <!-- Optional dynamic content here -->
                    </div>
                </div>`;
            headerElement.insertAdjacentHTML('beforeend', bannerImage); // Add the default header image
        }

        // Check if the user is logged in and update the user icon
        fetch('/users/check-session')
            .then(response => {
                if (response.ok) {
                    // User is logged in
                    const userCircle = document.querySelector('.user-circle');
                    userCircle.innerHTML = `
                        <a href="user_dashboard.html"> 
                            <div class="user-icon">
                                <i class="fas fa-user"></i>
                            </div>
                        </a>`;
                }
            })
            .catch(() => {
                // User is not logged in; default to sign-up page
                const userCircle = document.querySelector('.user-circle');
                userCircle.innerHTML = `
                    <a href="sign_up.html"> 
                        <div class="user-icon">
                            <i class="fas fa-user"></i>
                        </div>
                    </a>`;
            });
    })
    .catch(error => console.error('Error loading header:', error));

// Load footer
fetch('footer.html')
    .then(response => response.text())
    .then(data => {
        document.getElementById('footer-placeholder').innerHTML = data;
    })
    .catch(error => console.error('Error loading footer:', error));
