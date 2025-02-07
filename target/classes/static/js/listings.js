// Sample data array to simulate listings from a database
const listings = [
    { id: 1, country: "USA", town: "New York", type: "rent", price: 2300, description: "Spacious 3-bedroom house", bedrooms: 3, bathrooms: 1.5, area: 1350, imageUrl: "../images/home 1.webp" },
    { id: 2, country: "USA", town: "Los Angeles", type: "new", price: 2650000, description: "Luxury 5-bedroom villa", bedrooms: 5, bathrooms: 2.5, area: 4225, imageUrl: "../images/banner5.jpg" },
    { id: 3, country: "Canada", town: "Toronto", type: "lease", price: 1500, description: "Modern Condominium", bedrooms: 2, bathrooms: 1, area: 1348, imageUrl: "../images/building.jpg" },
    { id: 4, country: "Canada", town: "Ottawa", type: "student", price: 750, description: "Affordable student housing", bedrooms: 1, bathrooms: 1, area: 400, imageUrl: "../images/image1.jpg" }
];

// Function to get the URL parameter for filtering
function getFilterType() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get("type") || "all";
}

// Function to apply filters based on user inputs
function applyFilters() {
    const country = document.getElementById('country').value;
    const town = document.getElementById('town').value;
    const propertyType = document.getElementById('propertyType').value;
    const contractType = document.getElementById('contractType').value;
    const beds = parseInt(document.getElementById('beds').value) || 0;
    const baths = parseInt(document.getElementById('baths').value) || 0;
    const minPrice = parseFloat(document.getElementById('minPrice').value) || 0;
    const maxPrice = parseFloat(document.getElementById('maxPrice').value) || Infinity;

    const filteredListings = listings.filter(listing => {
        return (!country || listing.country === country) &&
               (!town || listing.town === town) &&
               (!propertyType || listing.type === propertyType) &&
               (!contractType || listing.type === contractType) &&
               (listing.bedrooms >= beds) &&
               (listing.bathrooms >= baths) &&
               (listing.price >= minPrice && listing.price <= maxPrice);
    });

    generateListings(filteredListings);
}

// Function to generate listings based on the filtered data
function generateListings(filteredListings) {
    const listingsSection = document.getElementById('listings-section');
    listingsSection.innerHTML = ""; // Clear existing listings

    filteredListings.forEach(listing => {
        const listingCard = document.createElement('div');
        listingCard.classList.add('col-md-4', 'mb-4');

        listingCard.innerHTML = `
            <a href="listing_info.html?id=${listing.id}" class="card-link">
                <div class="card">
                    <img src="${listing.imageUrl}" class="card-img-top" alt="Property Image">
                    <div class="card-body">
                        <h5 class="card-title">$${listing.price.toLocaleString()}</h5>
                        <p class="card-text">${listing.description}</p>
                        <div class="d-flex justify-content-between">
                            <span><i class="fas fa-bed"></i> ${listing.bedrooms}</span>
                            <span><i class="fas fa-bath"></i> ${listing.bathrooms}</span>
                            <span><i class="fas fa-expand-arrows-alt"></i> ${listing.area} sqft</span>
                        </div>
                    </div>
                </div>
            </a>
        `;

        listingsSection.appendChild(listingCard);
    });
}

// Function to initialize the filters and display all listings on page load
document.addEventListener('DOMContentLoaded', () => {
    generateListings(listings);

    // Attach the applyFilters function to the search form
    const searchForm = document.querySelector('form');
    if (searchForm) {
        searchForm.addEventListener('submit', function(event) {
            event.preventDefault();
            applyFilters();
        });
    }
});
