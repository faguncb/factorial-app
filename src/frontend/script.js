async function computeFactorial() {
    const paradigm = document.getElementById('paradigm').value;
    const n = document.getElementById('number').value;
    if (n < 0 || n > 20) {
        document.getElementById('result').innerText = 'Please enter a number between 0 and 20.';
        return;
    }
    try {
        const response = await fetch(`/factorial/${paradigm}/${n}`);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const result = await response.text();
        document.getElementById('result').innerText = `Result: ${result}`;
    } catch (error) {
        document.getElementById('result').innerText = `Error: ${error.message}`;
    }
}