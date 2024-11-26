import React, { useState } from 'react';

const Dashboard = () => {
  const [filters, setFilters] = useState({
    difficulty: 'Easy',
    topics: [],
    numberOfProblems: 5,
  });

  const topicsList = ['Array', 'String', 'Tree', 'Graph', 'DP', 'Sorting', 'Searching'];

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFilters({ ...filters, [name]: value });
  };

  const handleTopicToggle = (topic) => {
    setFilters((prev) => ({
      ...prev,
      topics: prev.topics.includes(topic)
        ? prev.topics.filter((t) => t !== topic)
        : [...prev.topics, topic],
    }));
  };

  const handleSubmit = () => {
    alert(`Filters: ${JSON.stringify(filters)}`);
    // This is where you'd trigger a backend request with filters
  };

  return (
    <div className="min-h-screen bg-gray-100 p-8">
      {/* Header */}
      <div className="text-center mb-8">
        <h1 className="text-3xl font-bold text-gray-800">Practice Coding Problems</h1>
        <p className="text-lg text-gray-600 mt-2">Customize your practice session below.</p>
      </div>

      {/* Filters */}
      <div className="max-w-4xl mx-auto bg-white p-6 rounded-lg shadow-lg">
        <h2 className="text-xl font-semibold text-gray-700 mb-4">Set Your Filters</h2>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          {/* Difficulty Filter */}
          <div>
            <label className="block text-gray-700 font-medium mb-2">Difficulty</label>
            <select
              name="difficulty"
              value={filters.difficulty}
              onChange={handleChange}
              className="w-full p-2 border border-gray-300 rounded-lg"
            >
              <option value="Easy">Easy</option>
              <option value="Medium">Medium</option>
              <option value="Hard">Hard</option>
            </select>
          </div>

          {/* Number of Problems */}
          <div>
            <label className="block text-gray-700 font-medium mb-2">Number of Problems</label>
            <input
              type="number"
              name="numberOfProblems"
              value={filters.numberOfProblems}
              onChange={handleChange}
              min="1"
              max="100"
              className="w-full p-2 border border-gray-300 rounded-lg"
            />
          </div>
        </div>

        {/* Topics */}
        <div className="mt-6">
          <label className="block text-gray-700 font-medium mb-2">Select Topics</label>
          <div className="grid grid-cols-2 md:grid-cols-3 gap-4">
            {topicsList.map((topic) => (
              <button
                key={topic}
                type="button"
                onClick={() => handleTopicToggle(topic)}
                className={`p-2 rounded-lg border ${
                  filters.topics.includes(topic)
                    ? 'bg-blue-500 text-white border-blue-500'
                    : 'bg-white text-gray-700 border-gray-300'
                }`}
              >
                {topic}
              </button>
            ))}
          </div>
        </div>

        {/* Submit Button */}
        <div className="mt-8 text-center">
          <button
            onClick={handleSubmit}
            className="bg-blue-500 text-white px-6 py-3 rounded-lg hover:bg-blue-600 font-bold"
          >
            Start Practice
          </button>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
