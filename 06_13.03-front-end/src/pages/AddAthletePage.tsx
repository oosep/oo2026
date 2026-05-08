import { useState } from "react";
import { useNavigate } from "react-router-dom";

function AddAthletePage() {
  const navigate = useNavigate();
  const [nimi, setNimi] = useState("");
  const [riik, setRiik] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError("");

    try {
      const res = await fetch("http://localhost:8080/sportlased", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nimi, riik }),
      });

      if (!res.ok) throw new Error("Lisamine ebaõnnestus");

      setNimi("");
      setRiik("");
      navigate("/sportlased");
    } catch {
      setError("Sportlase lisamine ebaõnnestus");
    }
  };

  return (
    <div>
      <h2>Lisa sportlane</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Nimi: </label>
          <input value={nimi} onChange={e => setNimi(e.target.value)} required />
        </div>
        <div>
          <label>Riik: </label>
          <input value={riik} onChange={e => setRiik(e.target.value)} required />
        </div>
        <button type="submit">Lisa</button>
      </form>
      {error && <p>{error}</p>}
    </div>
  );
}

export default AddAthletePage;