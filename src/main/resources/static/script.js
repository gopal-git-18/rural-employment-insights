const apiBase = window.location.origin;

// Fetch and populate district dropdown
async function loadDistricts() {
    const res = await fetch(`${apiBase}/districts`);
    const districts = await res.json();

    const select = document.getElementById("districtSelect");
    select.innerHTML = "";

    districts.forEach(d => {
        const opt = document.createElement("option");
        opt.value = d.id;
        opt.textContent = `${d.name}, ${d.state}`;
        select.appendChild(opt);
    });

    select.addEventListener("change", () => {
        loadPerformance(select.value);
    });

    // Try auto-detect location
    detectLocation(districts);
}

// Detect user location and find nearest district
function detectLocation(districts) {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(async pos => {
            const lat = pos.coords.latitude;
            const lng = pos.coords.longitude;

            document.getElementById("status").textContent =
                `📍 Detecting district near (${lat.toFixed(2)}, ${lng.toFixed(2)})...`;

            const res = await fetch(`${apiBase}/districts/nearest?lat=${lat}&lng=${lng}`);
            const district = await res.json();

            document.getElementById("status").textContent =
                `✅ Auto-detected: ${district.name}, ${district.state}`;

            document.getElementById("districtSelect").value = district.id;
            loadPerformance(district.id);
        }, () => {
            document.getElementById("status").textContent =
                "⚠️ Location access denied. Please select district manually.";
        });
    } else {
        document.getElementById("status").textContent =
            "❌ Geolocation not supported. Please select district manually.";
    }
}

// Load performance data for selected district
async function loadPerformance(districtId) {
    const res = await fetch(`${apiBase}/districts/${districtId}/performance`);
    const data = await res.json();

    const tableBody = document.querySelector("#performanceTable tbody");
    tableBody.innerHTML = "";

    data.forEach(row => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>${row.month}</td>
            <td>${row.year}</td>
            <td>${row.workers}</td>
            <td>${row.wagesPaid.toFixed(2)}</td>
            <td>${row.households}</td>
        `;
        tableBody.appendChild(tr);
    });

    const selected = await fetch(`${apiBase}/districts/${districtId}`);
    const district = await selected.json();
    document.getElementById("districtName").textContent = `📊 ${district.name}, ${district.state}`;
}

// Load everything on page start
loadDistricts();
