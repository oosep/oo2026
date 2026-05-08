import { Link, Route, Routes } from 'react-router-dom'
import HomePage from './pages/HomePage'
import AddAthletePage from './pages/AddAthletePage'

function App() {
  return (
    <>
      <Link to="/sportlased"><button>Kõik sportlased</button></Link>
      <Link to="/lisa"><button>Lisa sportlane</button></Link>

      <Routes>
        <Route path="/sportlased" element={<HomePage />} />
        <Route path="/lisa" element={<AddAthletePage />} />
      </Routes>
    </>
  )
}

export default App