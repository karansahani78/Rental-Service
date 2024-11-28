document.addEventListener('DOMContentLoaded', function() {
    // Get the selected plan from the URL
    const urlParams = new URLSearchParams(window.location.search);
    const selectedPlan = urlParams.get('plan');

    // Plan details
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

    // Get the selected plan details
    const plan = plans[selectedPlan] || plans["1"];

    // Display the selected plan details
    const paymentDetailsDiv = document.getElementById('payment-details');
    paymentDetailsDiv.innerHTML = `
        <h2>${plan.name}</h2>
        <h3>${plan.price}</h3>
        <p>${plan.description}</p>
        <ul class="list-unstyled">
            ${plan.features.map(feature => `<li>• ${feature}</li>`).join('')}
        </ul>
    `;
});
