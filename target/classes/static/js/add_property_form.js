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

// Populate countries and provinces
function populateCountriesAndProvinces() {
    const countryDropdown = document.getElementById("country");
    const provinceDropdown = document.getElementById("province");

    // Disable province dropdown by default
    provinceDropdown.disabled = true;

    // Fetch countries and populate the dropdown
    fetch('/countries/all')
        .then(response => response.json())
        .then(countries => {
            countryDropdown.innerHTML = '<option value="" disabled selected>Select country</option>';
            countries.forEach(country => {
                const option = document.createElement("option");
                option.value = country.countryCode; // Value sent to the back-end
                option.textContent = country.countryName; // Display name for the user
                countryDropdown.appendChild(option);
            });
        })
        .catch(error => console.error("Error loading countries:", error));

    // Handle province dropdown based on country selection
    countryDropdown.addEventListener("change", function () {
        const selectedCountryCode = countryDropdown.value;

        if (selectedCountryCode === "CA" || selectedCountryCode === "US") {
            provinceDropdown.disabled = false; // Enable dropdown for Canada and US

            // Fetch provinces for the selected country code
            fetch(`/provinces/${selectedCountryCode}`)
                .then(response => response.json())
                .then(provinces => {
                    provinceDropdown.innerHTML = '<option value="" disabled selected>Select state/province</option>';
                    provinces.forEach(province => {
                        const option = document.createElement("option");
                        option.value = province.provinceCode; // Use province code as value
                        option.textContent = province.provinceName; // Display province name
                        provinceDropdown.appendChild(option);
                    });
                })
                .catch(error => {
                    console.error("Error loading provinces:", error);
                    provinceDropdown.innerHTML = '<option value="" disabled selected>Failed to load provinces</option>';
                });
        } else {
            provinceDropdown.disabled = true; // Disable dropdown for other countries
            provinceDropdown.innerHTML = '<option value="" disabled selected>Not applicable</option>';
        }
    });
}

// Populate property types
function populatePropertyTypes() {
    const propertyTypeDropdown = document.getElementById("propertyType");

    // Fetch property types and populate the dropdown
    fetch('/property-types/all')
        .then(response => response.json())
        .then(propertyTypes => {
            propertyTypeDropdown.innerHTML = '<option value="" disabled selected>Select property type</option>';
            propertyTypes.forEach(propertyType => {
                const option = document.createElement("option");
                option.value = propertyType.typeName;
                option.textContent = propertyType.typeName;
                propertyTypeDropdown.appendChild(option);
            });
        })
        .catch(error => console.error("Error loading property types:", error));
}

// Initialize functions when the DOM is fully loaded
document.addEventListener("DOMContentLoaded", function () {
    checkSession();
    populateCountriesAndProvinces();
    populatePropertyTypes();
});




