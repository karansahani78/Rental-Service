document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const selectedPlan = urlParams.get('plan');

    const plans = {
        "1": {
            name: "Tier 1",
            price: "$75 / month",
            description: "Perfect for those trying the system for the first time.",
            features: ["Listing placement", "Personal identification number (PIN)"]
        },
        "2": {
            name: "Tier 2",
            price: "$150 / month",
            description: "For those looking to target a specific audience.",
            features: ["Listing placement", "PIN", "15,000 viewers", "Social media presence", "Bi-weekly stats"]
        },
        "3": {
            name: "Tier 3",
            price: "$300 / month",
            description: "For international agents looking to connect globally.",
            features: ["Listing placement", "PIN", "15,000 viewers", "Social media presence", "Bi-weekly stats", "Weekly boost"]
        }
    };

    const plan = plans[selectedPlan] || plans["1"];
    const paymentDetailsDiv = document.getElementById('payment-details');
    paymentDetailsDiv.innerHTML = `
        <h2>${plan.name}</h2>
        <h3>${plan.price}</h3>
        <p>${plan.description}</p>
        <ul class="list-unstyled">
            ${plan.features.map(feature => `<li>• ${feature}</li>`).join('')}
        </ul>
    `;

    // Handle "Select" button click
    const selectButton = document.getElementById('select-plan');
    if (selectButton) {
        selectButton.addEventListener('click', function() {
            // Check if the user is logged in
            fetch('/users/check-session')
                .then(response => response.json())
                .then(data => {
                    if (data.error) {
                        alert('You need to log in first!');
                        window.location.href = '/sign_in.html'; // Redirect to login page
                    } else {
                        // User is logged in, update subscription tier
                        fetch(`/users/update-tier?tier=${selectedPlan}`, {
                            method: 'POST',
                        })
                        .then(response => response.json())
                        .then(responseData => {
                            alert('Subscription tier updated successfully!');
                            window.location.href = '/user_dashboard.html'; // Redirect to dashboard
                        });
                    }
                })
                .catch(error => {
                    console.error('Error checking session:', error);
                });
        });
    }
});
