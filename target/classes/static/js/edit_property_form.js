// Check session
function checkSession() {
    fetch('/users/check-session', { method: 'GET' })
        .then(response => {
            if (!response.ok) {
                window.location.href = "/sign_in.html?error=not_logged_in";
            }
        })
        .catch(() => {
            window.location.href = "/sign_in.html?error=not_logged_in";
        });
}

// Fetch property details for editing
function fetchPropertyDetails(propertyId) {
    fetch(`/properties/${propertyId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to fetch property details");
            }
            return response.json();
        })
        .then(property => {
            console.log('Fetched property details:', property);

            // Populate fields with property details
            document.getElementById('propertyId').value = property.propertyId;
            document.getElementById('userId').value = property.user.id;

            document.getElementById('title').value = property.title;
            document.getElementById('price').value = property.price;
            document.getElementById('description').value = property.description;
            document.getElementById('bedrooms').value = property.bedrooms;
            document.getElementById('bathrooms').value = property.bathrooms;
            document.getElementById('areaSqFt').value = property.areaSqFt;
            document.getElementById('street').value = property.address.street;
            document.getElementById('city').value = property.address.city;
            document.getElementById('postalCode').value = property.address.postalCode;
            document.getElementById('status').value = property.status.toLowerCase();
            populateCountriesAndProvinces(property.address.countryCode);
            populatePropertyTypes(property.propertyType.typeName);

            // Trigger province selection
            setTimeout(() => {
                document.getElementById('province').value = property.address.provinceCode;
            }, 500);
        })
        .catch(error => console.error("Error fetching property details:", error));
}



function populateCountriesAndProvinces(selectedCountryCode) {
    const countryDropdown = document.getElementById("country");
    const provinceDropdown = document.getElementById("province");

    // Disable province dropdown by default
    provinceDropdown.disabled = true;

    // Fetch countries and populate the dropdown
    fetch('/countries/all')
        .then(response => response.json())
        .then(countries => {
            countryDropdown.innerHTML = '<option value="" disabled>Select country</option>';
            countries.forEach(country => {
                const option = document.createElement("option");
                option.value = country.countryCode;
                option.textContent = country.countryName;
                countryDropdown.appendChild(option);
            });

            if (selectedCountryCode) {
                countryDropdown.value = selectedCountryCode;
                countryDropdown.dispatchEvent(new Event('change'));
            }
        })
        .catch(error => console.error("Error loading countries:", error));

    countryDropdown.addEventListener("change", function () {
        const selectedCountry = countryDropdown.value;

        if (selectedCountry === "US" || selectedCountry === "CA") {
            provinceDropdown.disabled = false;

            // Fetch provinces for the selected country code
            fetch(`/provinces/${selectedCountry}`)
                .then(response => response.json())
                .then(provinces => {
                    provinceDropdown.innerHTML = '<option value="" disabled selected>Select state/province</option>';
                    provinces.forEach(province => {
                        const option = document.createElement("option");
                        option.value = province.provinceCode;
                        option.textContent = province.provinceName;
                        provinceDropdown.appendChild(option);
                    });
                })
                .catch(error => {
                    console.error("Error loading provinces:", error);
                    provinceDropdown.innerHTML = '<option value="" disabled selected>Failed to load provinces</option>';
                });
        } else {
            provinceDropdown.disabled = true;
            provinceDropdown.innerHTML = '<option value="" disabled selected>Not applicable</option>';
        }
    });
}


function populatePropertyTypes(selectedPropertyType) {
    const propertyTypeDropdown = document.getElementById("propertyType");

    // Fetch property types and populate the dropdown
    fetch('/property-types/all')
        .then(response => response.json())
        .then(propertyTypes => {
            propertyTypeDropdown.innerHTML = '<option value="" disabled>Select property type</option>';
            propertyTypes.forEach(propertyType => {
                const option = document.createElement("option");
                option.value = propertyType.typeName; 
                option.textContent = propertyType.typeName; // Display name for the user
                propertyTypeDropdown.appendChild(option);
            });

            if (selectedPropertyType) {
                propertyTypeDropdown.value = selectedPropertyType;
            }
        })
        .catch(error => console.error("Error loading property types:", error));
}



// Initialize form
document.addEventListener("DOMContentLoaded", function () {
    checkSession();

    const urlParams = new URLSearchParams(window.location.search);
    const propertyId = urlParams.get("propertyId");

    if (propertyId) {
        fetchPropertyDetails(propertyId);
    } else {
        console.error("No propertyId found in URL");
    }

    populateCountriesAndProvinces();

    // Add submit event listener
    document.getElementById('propertyForm').addEventListener('submit', function (event) {
        event.preventDefault(); 

        const propertyData = {
            propertyId: document.getElementById('propertyId').value,
            user: { id: document.getElementById('userId').value },
            title: document.getElementById('title').value,
            description: document.getElementById('description').value,
            price: parseFloat(document.getElementById('price').value),
            areaSqFt: parseFloat(document.getElementById('areaSqFt').value),
            bedrooms: parseFloat(document.getElementById('bedrooms').value),
            bathrooms: parseFloat(document.getElementById('bathrooms').value),
            status: document.getElementById('status').value.toUpperCase(),
            address: {
                street: document.getElementById('street').value,
                city: document.getElementById('city').value,
                postalCode: document.getElementById('postalCode').value,
                provinceCode: document.getElementById('province').value,
                countryCode: document.getElementById('country').value
            },
            propertyType: {
                typeName: document.getElementById('propertyType').value
            }
        };
        console.log('Property data being sent:', propertyData);
        fetch('/properties/update', {
            method: 'POST', 
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(propertyData)
        })
            .then(response => {
                if (response.ok) {
                    alert('Property updated successfully');
                    window.location.href = '/user_dashboard.html'; // Redirect to dashboard
                } else {
                    throw new Error('Failed to update property');
                }
            })
            .catch(error => console.error('Error updating property:', error));
        
    });

    document.getElementById("deleteListingButton").addEventListener("click", function () {
        if (confirm("Are you sure you want to delete this listing? This action cannot be undone.")) {
            const propertyId = document.getElementById("propertyId").value;

            fetch(`/properties/${propertyId}`, {
                method: "DELETE",
            })
                .then((response) => {
                    if (response.ok) {
                        alert("Listing deleted successfully");
                        window.location.href = "/user_dashboard.html"; // Redirect to dashboard
                    } else {
                        throw new Error("Failed to delete the listing");
                    }
                })
                .catch((error) => console.error("Error deleting property:", error));
        }
    });
});


