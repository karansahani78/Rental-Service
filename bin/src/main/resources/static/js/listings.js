// Sample data array to simulate listings from a database
const listings = [
    { type: "rent", price: "$1,500 / month", description: "Cozy 2-bedroom apartment", bedrooms: 2, bathrooms: 1, area: "850 sqft", imageUrl: "../images/logo.png" },
    { type: "lease", price: "$2,200 / month", description: "Spacious 3-bedroom house", bedrooms: 3, bathrooms: 2, area: "1,200 sqft", imageUrl: "../images/logo.png" },
    { type: "new", price: "$3,000 / month", description: "Luxury 4-bedroom villa", bedrooms: 4, bathrooms: 3, area: "2,500 sqft", imageUrl: "../images/logo.png" },
    { type: "student", price: "$1,000 / month", description: "Affordable student housing", bedrooms: 1, bathrooms: 1, area: "400 sqft", imageUrl: "../images/logo.png" }
];

// Function to get the URL parameter for filtering
function getFilterType() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get("type") || "all";
}

// Function to generate filtered listings based on the filter type
function generateFilteredListings() {
    const listingsSection = document.getElementById('listings-section');
    listingsSection.innerHTML = ""; // Clear existing listings

    const filterType = getFilterType();
    const filteredListings = filterType === "all" ? listings : listings.filter(listing => listing.type === filterType);

    // Generate and display each listing
    filteredListings.forEach(listing => {
        const listingCard = document.createElement('div');
        listingCard.classList.add('col-md-4', 'mb-4');

        listingCard.innerHTML = `
            <div class="card">
                <img src="${listing.imageUrl}" class="card-img-top" alt="Property Image">
                <div class="card-body">
                    <h5 class="card-title">${listing.price}</h5>
                    <p class="card-text">${listing.description}</p>
                    <div class="d-flex justify-content-between">
                        <span><i class="fas fa-bed"></i> ${listing.bedrooms}</span>
                        <span><i class="fas fa-bath"></i> ${listing.bathrooms}</span>
                        <span><i class="fas fa-expand-arrows-alt"></i> ${listing.area}</span>
                    </div>
                </div>
            </div>
        `;
        
        listingsSection.appendChild(listingCard);
    });
}

// Run the filter function on page load
document.addEventListener('DOMContentLoaded', generateFilteredListings);
