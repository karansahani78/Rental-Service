// Mock data
const listings = [
    { id: 1, type: "rent", price: "$2,300 / month", description: "Spacious 3-bedroom house", bedrooms: 3, bathrooms: 1.5, area: "1,350 sqft", imageUrl: "../images/home%201.webp" },
    { id: 2, type: "new", price: "$2,650,000", description: "Luxury 5-bedroom villa", bedrooms: 5, bathrooms: 2.5, area: "4,225 sqft", imageUrl: "../images/banner5.jpg" },
    { id: 3, type: "lease", price: "$1,500 / month", description: "Modern Condominium", bedrooms: 2, bathrooms: 1, area: "1,348 sqft", imageUrl: "../images/building.jpg" },
    { id: 4, type: "student", price: "$750 / month", description: "Affordable student housing", bedrooms: 1, bathrooms: 1, area: "400 sqft", imageUrl: "../images/image1.jpg" }
];

// Function to get query parameters
function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

// Function to display listing details
function displayListingDetails() {
    const listingId = getQueryParam('id');
    const listing = listings.find(listing => listing.id == listingId);

    if (listing) {
        const detailsContainer = document.getElementById('listing-details');
        detailsContainer.innerHTML = `
            <div class="col-12">
                <h2>${listing.description}</h2>
                <img src="${listing.imageUrl}" class="img-fluid mb-4" alt="Property Image">
                <h4>Price: ${listing.price}</h4>
                <p>Bedrooms: ${listing.bedrooms}</p>
                <p>Bathrooms: ${listing.bathrooms}</p>
                <p>Area: ${listing.area}</p>
                <a href="properties.html" class="btn btn-secondary mt-3">Back to Listings</a>
            </div>
        `;
    } else {
        document.getElementById('listing-details').innerHTML = `<h3>Listing not found</h3>`;
    }
}

// Wait for the DOM to fully load before running the script
document.addEventListener('DOMContentLoaded', displayListingDetails);
