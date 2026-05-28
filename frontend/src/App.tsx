import { createTheme, CssBaseline, ThemeProvider } from "@mui/material";
import { TransactionDashboard } from "./components/TransactionDashboard";

const darkTheme = createTheme({
  palette: {
    mode: 'dark',
    background: {
      default: '#0a192f',
      paper: '#112240'
    },
    primary: {
      main: '#64ffda',
    },
  },
  typography: {
    fontFamily: '"Inter", "Roboto", "Helvetica", "Arial", sans-serif',
  },
});

function App() {
  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      <TransactionDashboard />
    </ThemeProvider>
  );
}

export default App;