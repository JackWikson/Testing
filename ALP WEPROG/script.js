document.addEventListener('DOMContentLoaded', function() {
    const concerts = [
        { 
            id: 1, 
            name: "JKT48 12th Anniversary: Thank You for the Memories", 
            artist: "JKT48 (All Members)",
            date: "15 Desember 2024",  
            venue: "ICE BSD, Tangerang",
            poster:"image1.png",
            ticketTypes: [
                { name: "VIP", price: 1500000 },
                { name: "Premium", price: 800000 },
                { name: "Regular", price: 300000 }
            ]
        },
        { 
            id: 2, 
            name: "Agnez Mo - The Icon Returns", 
            artist: "Agnez Mo",
            date: "20 Januari 2025", 
            venue: "The Kasablanka Hall, Jakarta",
            poster:"image2.png",
            ticketTypes: [
                { name: "Gold", price: 2500000 },
                { name: "Silver", price: 1000000 },
                { name: "Bronze", price: 500000 }
            ]
        },
        { 
            id: 3, 
            name: "Sounds of Indonesia", 
            artist: "Noah, Tulus, Nadin Amizah",
            date: "1-2 Maret 2025", 
            venue: "Lapangan DKI Senayan, Jakarta",
            ticketTypes: [
                { name: "1 Day Pass", price: 400000 },
                { name: "2 Days Pass", price: 700000 }
            ]
        },
        { 
            id: 4, 
            name: "Dangdut Tembus Pandang", 
            artist: "Via Vallen, Nella Kharisma, Lesti",
            date: "10 April 2025", 
            venue: "Lapangan Monas, Jakarta",
            ticketTypes: [
                { name: "Free Area", price: 100000 },
                { name: "VIP", price: 300000 }
            ]
        }
    ];

    // Elemen DOM
    const concertListDiv = document.getElementById('concertList');
    const concertSelect = document.getElementById('concert');
    const ticketTypeSelect = document.getElementById('ticketType');
    const quantityInput = document.getElementById('quantity');
    const priceDisplay = document.getElementById('priceDisplay');
    const totalDisplay = document.getElementById('totalDisplay');
    const ticketForm = document.getElementById('ticketForm');

    // 1. Tampilkan daftar konser
    concerts.forEach(concert => {
        // Buat card konser
        const concertCard = document.createElement('div');
        concertCard.className = 'concert-card';
        concertCard.innerHTML = `
            <h3>${concert.name}</h3>
            <p><strong>Artis:</strong> ${concert.artist}</p>
            <p><strong>Tanggal:</strong> ${concert.date}</p>
            <p><strong>Tempat:</strong> ${concert.venue}</p>
            <a href="info.html?id=${concert.id}" class="info-link">Lihat Info Lengkap</a>
        `;
        concertListDiv.appendChild(concertCard);

        // Isi dropdown konser
        const option = document.createElement('option');
        option.value = concert.id;
        option.textContent = `${concert.name} - ${concert.date}`;
        concertSelect.appendChild(option);
    });

    // 2. Update jenis tiket saat konser dipilih
    concertSelect.addEventListener('change', function() {
        const selectedConcertId = parseInt(this.value);
        const concert = concerts.find(c => c.id === selectedConcertId);
        
        // Kosongkan dan isi ulang opsi tiket
        ticketTypeSelect.innerHTML = '<option value="">-- Pilih Jenis --</option>';
        
        if (concert) {
            concert.ticketTypes.forEach(ticket => {
                const option = document.createElement('option');
                option.value = ticket.name;
                option.textContent = `${ticket.name} (Rp ${ticket.price.toLocaleString('id-ID')})`;
                ticketTypeSelect.appendChild(option);
            });
        }
        
        updatePrice();
    });

    // 3. Fungsi untuk update harga
    function updatePrice() {
        const selectedConcertId = parseInt(concertSelect.value);
        const selectedTicketType = ticketTypeSelect.value;
        const quantity = parseInt(quantityInput.value) || 0;
        
        if (selectedConcertId && selectedTicketType) {
            const concert = concerts.find(c => c.id === selectedConcertId);
            const ticket = concert.ticketTypes.find(t => t.name === selectedTicketType);
            
            priceDisplay.textContent = `Harga per tiket: Rp ${ticket.price.toLocaleString('id-ID')}`;
            totalDisplay.textContent = `Total: Rp ${(ticket.price * quantity).toLocaleString('id-ID')}`;
        } else {
            priceDisplay.textContent = '';
            totalDisplay.textContent = '';
        }
    }

    // 4. Event listeners untuk update harga
    ticketTypeSelect.addEventListener('change', updatePrice);
    quantityInput.addEventListener('input', updatePrice);

    // 5. Form submission
    ticketForm.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const selectedConcertId = parseInt(concertSelect.value);
        const concert = concerts.find(c => c.id === selectedConcertId);
        const selectedTicketType = ticketTypeSelect.value;
        const ticket = concert.ticketTypes.find(t => t.name === selectedTicketType);
        const quantity = parseInt(quantityInput.value);
        
        const formData = {
            concert: concertSelect.options[concertSelect.selectedIndex].text,
            name: document.getElementById('name').value,
            email: document.getElementById('email').value,
            ticketType: selectedTicketType,
            price: ticket.price,
            quantity: quantity,
            total: ticket.price * quantity
        };

        // Simpan ke localStorage
        localStorage.setItem('lastBooking', JSON.stringify(formData));
        
        alert(`Terima kasih ${formData.name}!\n\nPesanan Anda:\nKonser: ${formData.concert}\nTiket: ${formData.ticketType} (Rp ${formData.price.toLocaleString('id-ID')})\nJumlah: ${formData.quantity}\nTotal: Rp ${formData.total.toLocaleString('id-ID')}`);
        
        // Reset form
        ticketForm.reset();
        priceDisplay.textContent = '';
        totalDisplay.textContent = '';
    });
});