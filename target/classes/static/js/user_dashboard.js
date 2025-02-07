// Fetch user information
fetch('/users/check-session')
    .then(response => {
        if (!response.ok) {
            throw new Error('User not logged in');
        }
        return response.json();
    })
    .then(user => {
        // Fill in the fields dynamically
        document.getElementById('user-name').textContent = `${user.userName} ${user.lastName}`;
        document.getElementById('user-email').textContent = user.email;
        document.getElementById('user-phone').textContent = user.phoneNumber || 'N/A';

        const subscriptionTierDisplay = (() => {
            switch (user.subscriptionTier) {
                case 'TIER1': return 'Tier 1';
                case 'TIER2': return 'Tier 2';
                case 'TIER3': return 'Tier 3';
                case 'NOTIER': return 'Free to Use';
                default: return 'Unknown';
            }
        })();
        document.getElementById('user-tier').textContent = subscriptionTierDisplay;
    })
    .catch(error => {
        console.error('Error fetching user info:', error);
        document.getElementById('user-info').textContent = 'Error loading user information. Please log in.';
        window.location.href = '/sign_in.html';
    });




// Fetch properties and render dynamically
fetch("/properties/user")
    .then((response) => {
        if (!response.ok) {
            throw new Error("Error fetching properties");
        }
        return response.json();
    })
    .then((properties) => {
        const container = document.getElementById("user-properties-section");
        if (properties.length === 0) {
            container.innerHTML = `
                <div class="col-12 text-center">
                    <p>You haven't posted any properties yet.</p>
                    <a href="add_property_form.html" class="btn btn-primary">Add a Property</a>
                </div>
            `;
        } else {
            container.innerHTML = properties
                .map(
                    (property) => `
                    <div class="col-md-6 col-lg-4 mb-4">
                        <div class="card property-card">
                            <div class="card-header">
                                <h5 class="card-title">${property.title}</h5>
                                <p class="property-price">$${property.price.toFixed(2)}</p>
                            </div>
                            <div class="card-body">
                                <p class="property-type">Type: ${property.propertyType}</p>
                                <div class="property-details">
                                    <span><i class="fas fa-bed"></i> ${property.bedrooms}</span>
                                    <span><i class="fas fa-bath"></i> ${property.bathrooms}</span>
                                    <span><i class="fas fa-expand-arrows-alt"></i> ${property.areaSqFt} sqft</span>
                                </div>
                            </div>
                            <div class="card-footer">
                                <span class="property-status">${capitalizeFirstLetter(property.status)}</span>
                                <a href="edit_property_form.html?propertyId=${property.propertyId}" class="btn btn-warning">Edit</a>

                            </div>
                        </div>
                    </div>
                    `
                )
                .join("");
        }
    })
    .catch((error) => {
        console.error("Error fetching properties:", error);
        document.getElementById("user-properties-section").innerHTML = `
            <div class="col-12 text-center">
                <p>Error loading properties. Please try again later.</p>
            </div>
        `;
    });

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}


// Function to handle property deletion
function deleteProperty(propertyId) {
    if (confirm('Are you sure you want to delete this property?')) {
        fetch(`/properties/delete/${propertyId}`, { method: 'DELETE' })
            .then(response => {
                if (response.ok) {
                    alert('Property deleted successfully');
                    location.reload(); // Reload the dashboard
                } else {
                    alert('Failed to delete property. Please try again.');
                }
            })
            .catch(error => console.error('Error deleting property:', error));
    }
}

// Handle logout
document.getElementById('logout-button').addEventListener('click', () => {
    fetch('/users/logout', { method: 'GET' })
        .then(response => {
            if (response.ok) {
                window.location.href = '/index.html';
            } else {
                alert('Failed to log out. Please try again.');
            }
        })
        .catch(error => console.error('Error logging out:', error));
});
